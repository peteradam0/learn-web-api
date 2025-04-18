package learn.web.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "VideoEvent")
public class VideoEvent {

    @Id
    private String id;
    private String name;
    private String description;
    private String imageUrl;
    private Organization organization;
    private List<User> users;
    private boolean active;
    private User organizer;
    private String roomId;
    private Room room;
    private String date;

}
