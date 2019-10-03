package com.mb11.farmcraft.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;

public interface PlayerTickCallback {
    Event<PlayerTickCallback> EVENT = EventFactory.createArrayBacked(PlayerTickCallback.class,
            (listeners) -> (player) -> {
                for (PlayerTickCallback event : listeners) {
                    ActionResult result = event.onPlayerTick(player);
                    if(result != ActionResult.PASS) {
                        return result;
                    }
                }
                return ActionResult.FAIL;
    });

    ActionResult onPlayerTick(PlayerEntity player);
}
