package stan.ripto.easyrepair.key;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import org.lwjgl.glfw.GLFW;
import stan.ripto.easyrepair.datagen.client.lang.TranslateKeys;

public class EasyRepairKeyMappings {
    public static final KeyMapping OPEN_POUCH_INVENTORY = new KeyMapping(
                    TranslateKeys.OPEN_POUCH_INVENTORY,
                    KeyConflictContext.IN_GAME,
                    KeyModifier.SHIFT,
                    InputConstants.Type.KEYSYM,
                    GLFW.GLFW_KEY_R,
                    TranslateKeys.KEY_CATEGORY
    );
}
