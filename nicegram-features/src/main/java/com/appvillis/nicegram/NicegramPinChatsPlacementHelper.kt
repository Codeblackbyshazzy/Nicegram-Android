package com.appvillis.nicegram

import android.content.Context
import com.appvillis.core_domain.entry.placements.PinnedChatsPlacementEntry
import dagger.hilt.EntryPoints

object NicegramPinChatsPlacementHelper {
    private fun entryPoint(context: Context) = EntryPoints
        .get(context.applicationContext, NicegramAssistantEntryPoint::class.java)

    fun getPinChatsPlacements(context: Context) = entryPoint(context).getPinChatsPlacementsUseCase().noFilters()

    fun isPinnedChatHidden(context: Context, pin: PinnedChatsPlacementEntry) =
        entryPoint(context).getPinChatsPlacementsUseCase().pinPlacementIsHidden(pin)

    fun setPinnedChatHidden(context: Context, id: String, hidden: Boolean) {
        if (hidden) entryPoint(context).hidePlacementUseCase().invoke(id)
        else entryPoint(context).hidePlacementUseCase().unhide(id)
    }
}
