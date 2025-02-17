@file:Suppress("ktlint:standard:property-naming")

package app.revanced.patches.youtube.utils.playservice

import app.revanced.patcher.patch.resourcePatch
import app.revanced.util.findElementByAttributeValueOrThrow

var is_18_31_or_greater = false
    private set
var is_18_34_or_greater = false
    private set
var is_18_39_or_greater = false
    private set
var is_18_42_or_greater = false
    private set
var is_18_49_or_greater = false
    private set
var is_19_02_or_greater = false
    private set
var is_19_15_or_greater = false
    private set
var is_19_23_or_greater = false
    private set
var is_19_25_or_greater = false
    private set
var is_19_28_or_greater = false
    private set
var is_19_32_or_greater = false
    private set
var is_19_44_or_greater = false
    private set

val versionCheckPatch = resourcePatch(
    description = "versionCheckPatch",
) {
    execute {
        // The app version is missing from the decompiled manifest,
        // so instead use the Google Play services version and compare against specific releases.
        val playStoreServicesVersion = document("res/values/integers.xml").use { document ->
            document.documentElement.childNodes.findElementByAttributeValueOrThrow(
                "name",
                "google_play_services_version",
            ).textContent.toInt()
        }

        // All bug fix releases always seem to use the same play store version as the minor version.
        is_18_31_or_greater = 233200000 <= playStoreServicesVersion
        is_18_34_or_greater = 233500000 <= playStoreServicesVersion
        is_18_39_or_greater = 234000000 <= playStoreServicesVersion
        is_18_42_or_greater = 234302000 <= playStoreServicesVersion
        is_18_49_or_greater = 235000000 <= playStoreServicesVersion
        is_19_02_or_greater = 240204000 < playStoreServicesVersion
        is_19_15_or_greater = 241602000 <= playStoreServicesVersion
        is_19_23_or_greater = 242402000 <= playStoreServicesVersion
        is_19_25_or_greater = 242599000 <= playStoreServicesVersion
        is_19_28_or_greater = 242905000 <= playStoreServicesVersion
        is_19_32_or_greater = 243305000 <= playStoreServicesVersion
        is_19_44_or_greater = 244505000 <= playStoreServicesVersion
    }
}
