package hello.typeconverter.converter;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import hello.typeconverter.convertor.IntegerToStringConverter;
import hello.typeconverter.convertor.IpPortToStringConverter;
import hello.typeconverter.convertor.StringToIntegerConverter;
import hello.typeconverter.convertor.StringToIpPortConverter;
import hello.typeconverter.type.IpPort;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.support.DefaultConversionService;

public class ConversionServiceTest {

    @Test
    void conversionService(){
        DefaultConversionService conversionService = new DefaultConversionService();
        conversionService.addConverter(new StringToIntegerConverter());
        conversionService.addConverter(new IntegerToStringConverter());
        conversionService.addConverter(new StringToIpPortConverter());
        conversionService.addConverter(new IpPortToStringConverter());

        assertThat(conversionService.convert("10", Integer.class)).isEqualTo(10);
        assertThat(conversionService.convert(10, String.class)).isEqualTo("10");
        assertThat(conversionService.convert("127.0.0.1:8080", IpPort.class)).isEqualTo(new IpPort("127.0.0.1",8080));
        assertThat(conversionService.convert(new IpPort("127.0.0.1",8080), String.class)).isEqualTo("127.0.0.1:8080");
    }
}
