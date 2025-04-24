package formData;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PassengerFormData {
    private final String surname;
    private final String name;
    private final String patronymic;
    private final String birthday;
    private final String documentNumber;

}
