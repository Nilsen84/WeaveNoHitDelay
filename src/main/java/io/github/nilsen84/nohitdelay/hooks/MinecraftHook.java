package io.github.nilsen84.nohitdelay.hooks;

import club.maxstats.weave.loader.api.Hook;
import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.IntInsnNode;

public class MinecraftHook extends Hook {
    public MinecraftHook() {
        super("net/minecraft/client/Minecraft");
    }

    @Override
    public void transform(@NotNull ClassNode classNode, @NotNull AssemblerConfig assemblerConfig) {
        classNode.methods.stream()
                .filter(m -> m.name.equals("clickMouse"))
                .findFirst().orElseThrow()
                .instructions.forEach(i -> {
                    if (i.getOpcode() == Opcodes.BIPUSH && ((IntInsnNode) i).operand == 10) {
                        ((IntInsnNode) i).operand = 0;
                    }
                });
    }
}
