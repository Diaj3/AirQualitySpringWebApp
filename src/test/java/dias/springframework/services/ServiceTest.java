package dias.springframework.services;

import dias.springframework.domain.Location;
import dias.springframework.repositories.LocationRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.BDDMockito.given;

@SpringBootTest
@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class ServiceTest {

    @Mock
    private LocationRepository locationRepository;

    @InjectMocks
    private LocationService locationService ;

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void getLocationDetails_returnsLocationInfo() {
        given( locationRepository.findById(8379)).willReturn(java.util.Optional.of(new Location(8379, "Lisboa")));
        Location location = locationService.getLocationById(8379);
        Assertions.assertThat( location.getName()).isEqualTo("Lisboa");
    }
}