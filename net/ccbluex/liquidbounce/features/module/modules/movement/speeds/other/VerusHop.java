/*
 * Decompiled with CFR 0.152.
 */
package net.ccbluex.liquidbounce.features.module.modules.movement.speeds.other;

import net.ccbluex.liquidbounce.event.MoveEvent;
import net.ccbluex.liquidbounce.features.module.modules.movement.speeds.SpeedMode;
import net.ccbluex.liquidbounce.utils.MovementUtils;

public class VerusHop
extends SpeedMode {
    public VerusHop() {
        super("VerusHop");
    }

    @Override
    public void onMotion() {
    }

    @Override
    public void onUpdate() {
        if (!(VerusHop.mc2.field_71439_g.field_70134_J || VerusHop.mc2.field_71439_g.func_180799_ab() || VerusHop.mc2.field_71439_g.func_70090_H() || VerusHop.mc2.field_71439_g.func_70617_f_() || VerusHop.mc2.field_71439_g.field_184239_as != null || !MovementUtils.isMoving())) {
            VerusHop.mc2.field_71474_y.field_74314_A.field_74513_e = false;
            if (VerusHop.mc2.field_71439_g.field_70122_E) {
                VerusHop.mc2.field_71439_g.func_70664_aZ();
                MovementUtils.strafe(0.48f);
            }
            MovementUtils.strafe();
        }
    }

    @Override
    public void onMove(MoveEvent event) {
    }
}

