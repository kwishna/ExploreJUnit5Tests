package org.kepler.fundamentals.extensions.resolvers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.kepler.fundamentals.tags.JsonSource;

import java.io.InputStream;
import java.lang.reflect.Executable;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class BeforeAllAfterAllParameterResolver implements ParameterResolver {
    public BeforeAllAfterAllParameterResolver() {
    }

    private static final Gson gson = new Gson();

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        Parameter parameter = parameterContext.getParameter();
        Class<?> type = parameter.getType();
        return type.isAssignableFrom(BeforeAllWrapper.class) || type.isAssignableFrom(AfterAllWrapper.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        Executable executable = parameterContext.getDeclaringExecutable();
        Parameter parameter = parameterContext.getParameter();
        Class<?> type = parameter.getType();

        if (!BeforeAllWrapper.class.equals(type) && !AfterAllWrapper.class.equals(type)) {
            return null;
        }
        else {
            JsonSource annotation = executable.getAnnotation(JsonSource.class);
            if (null != annotation) {
                try {
                    Class<?> testClass = extensionContext.getRequiredTestClass();
                    InputStream stream = testClass.getResourceAsStream(annotation.filePath());
                    assert stream != null;
                    return this.parseJson(IOUtils.toString(stream, StandardCharsets.UTF_8), type, annotation.annotationType());
                } catch (Exception e) {
                    return null;
                }
            }
            else {
                return null;
            }
        }
    }

//    private <T> T parseJson(String data, Type wrapperType, Class<T> clazz) {
//        return gson.fromJson(data, TypeToken.getParameterized(wrapperType, new Type[]{clazz}).getType());
//    }

    private <T> T parseJson(String data, Type wrapperType, Class<T> clazz) {
        if (!(wrapperType instanceof Class)) {
            throw new IllegalArgumentException("wrapperType must be a Class");
        }
        Class<?> rawType = (Class<?>) wrapperType;
        Type type = TypeToken.getParameterized(rawType, clazz).getType();
        return gson.fromJson(data, type);
    }
}