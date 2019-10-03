package com.mb11.farmcraft.mixins;

import com.mb11.farmcraft.event.PlayerTickCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @Inject(at = @At("HEAD"), method = "tick", cancellable = true)
    private void onPlayerTick(final CallbackInfo info) {
        ActionResult result = PlayerTickCallback.EVENT.invoker().onPlayerTick((PlayerEntity) (Object) this);
        if(result == ActionResult.FAIL) {
            info.cancel();
        }
    }
}
