package bus.reservation.system.forms;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BookForm {
    @NotNull
    private String userEmail;

    @NotNull
    private int scheduleId;
}
