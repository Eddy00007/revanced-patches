package app.revanced.patches.youtube.general.navigation

import app.revanced.util.fingerprint.legacyFingerprint
import app.revanced.util.or
import com.android.tools.smali.dexlib2.AccessFlags
import com.android.tools.smali.dexlib2.Opcode

internal val autoMotiveFingerprint = legacyFingerprint(
    name = "autoMotiveFingerprint",
    opcodes = listOf(
        Opcode.GOTO,
        Opcode.INVOKE_STATIC,
        Opcode.MOVE_RESULT,
        Opcode.IF_EQZ
    ),
    strings = listOf("Android Automotive")
)

internal val pivotBarChangedFingerprint = legacyFingerprint(
    name = "pivotBarChangedFingerprint",
    returnType = "V",
    opcodes = listOf(
        Opcode.INVOKE_STATIC,
        Opcode.MOVE_RESULT
    ),
    customFingerprint = { method, _ ->
        method.definingClass.endsWith("/PivotBar;")
                && method.name == "onConfigurationChanged"
    }
)

internal val pivotBarSetTextFingerprint = legacyFingerprint(
    name = "pivotBarSetTextFingerprint",
    returnType = "V",
    accessFlags = AccessFlags.PUBLIC or AccessFlags.CONSTRUCTOR,
    parameters = listOf(
        "Lcom/google/android/libraries/youtube/rendering/ui/pivotbar/PivotBar;",
        "Landroid/widget/TextView;",
        "Ljava/lang/CharSequence;"
    ),
    opcodes = listOf(
        Opcode.INVOKE_VIRTUAL,
        Opcode.RETURN_VOID
    ),
    customFingerprint = { method, _ -> method.name == "<init>" }
)

internal val pivotBarStyleFingerprint = legacyFingerprint(
    name = "pivotBarStyleFingerprint",
    returnType = "V",
    parameters = listOf("L"),
    opcodes = listOf(
        Opcode.INVOKE_STATIC,
        Opcode.MOVE_RESULT,
        Opcode.XOR_INT_2ADDR
    ),
    customFingerprint = { method, _ ->
        method.definingClass.endsWith("/PivotBar;")
    }
)

internal val translucentNavigationBarFingerprint = legacyFingerprint(
    name = "translucentNavigationBarFingerprint",
    literals = listOf(45630927L),
)