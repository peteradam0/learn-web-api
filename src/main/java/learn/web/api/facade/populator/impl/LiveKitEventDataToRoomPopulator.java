package learn.web.api.facade.populator.impl;

import learn.web.api.facade.dto.LiveKitEventData;
import learn.web.api.facade.populator.Populator;
import learn.web.api.model.Room;
import org.springframework.stereotype.Component;

@Component
public class LiveKitEventDataToRoomPopulator implements Populator<LiveKitEventData, Room> {

    @Override
    public void populate(LiveKitEventData source, Room target) {
        target.setRoomId(source.getRoom().getName());
        target.setSid(source.getRoom().getSid());
    }
}
