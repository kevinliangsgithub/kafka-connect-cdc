package io.confluent.kafka.connect.cdc.xstream.docker;

import com.palantir.docker.compose.DockerComposeRule;
import com.palantir.docker.compose.connection.Container;
import com.palantir.docker.compose.connection.DockerPort;
import io.confluent.kafka.connect.cdc.docker.DockerExtension;
import io.confluent.kafka.connect.cdc.docker.SettingsExtension;
import io.confluent.kafka.connect.cdc.xstream.XStreamTestConstants;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

public class OracleSettingsExtension extends SettingsExtension {
  @Override
  protected List<Class<? extends Annotation>> annotationClasses() {
    return Arrays.asList(OracleSettings.class);
  }

  @Override
  protected Object handleResolve(ParameterContext parameterContext, ExtensionContext extensionContext, Annotation annotation, DockerComposeRule docker) throws ParameterResolutionException {
    Container container = docker.containers().container(XStreamTestConstants.ORACLE_CONTAINER);
    DockerPort dockerPort = container.port(XStreamTestConstants.ORACLE_PORT);
    return XStreamTestConstants.settings(dockerPort.getIp(), dockerPort.getExternalPort());
  }
}
