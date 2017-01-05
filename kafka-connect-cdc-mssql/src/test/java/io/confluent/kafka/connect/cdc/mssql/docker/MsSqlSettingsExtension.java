package io.confluent.kafka.connect.cdc.mssql.docker;

import com.palantir.docker.compose.DockerComposeRule;
import com.palantir.docker.compose.connection.Container;
import com.palantir.docker.compose.connection.DockerPort;
import io.confluent.kafka.connect.cdc.docker.SettingsExtension;
import io.confluent.kafka.connect.cdc.mssql.MsSqlTestConstants;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

public class MsSqlSettingsExtension extends SettingsExtension {
  @Override
  protected List<Class<? extends Annotation>> annotationClasses() {
    return Arrays.asList(MsSqlSettings.class);
  }

  @Override
  protected Object handleResolve(ParameterContext parameterContext, ExtensionContext extensionContext, Annotation annotation, DockerComposeRule docker) throws ParameterResolutionException {
    Container container = docker.containers().container(MsSqlTestConstants.CONTAINER_NAME);
    DockerPort dockerPort = container.port(MsSqlTestConstants.PORT);
    return MsSqlTestConstants.settings(dockerPort.getIp(), dockerPort.getExternalPort());
  }
}
