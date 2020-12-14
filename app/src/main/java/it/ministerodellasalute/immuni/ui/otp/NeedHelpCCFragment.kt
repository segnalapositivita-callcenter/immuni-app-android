/*
 * Copyright (C) 2020 Presidenza del Consiglio dei Ministri.
 * Please refer to the AUTHORS file for more information.
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package it.ministerodellasalute.immuni.ui.otp

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import it.ministerodellasalute.immuni.DataUploadDirections
import it.ministerodellasalute.immuni.R
import it.ministerodellasalute.immuni.SettingsNavDirections
import it.ministerodellasalute.immuni.extensions.utils.coloredClickable
import it.ministerodellasalute.immuni.extensions.view.getColorCompat
import it.ministerodellasalute.immuni.extensions.view.setSafeOnClickListener
import it.ministerodellasalute.immuni.util.startPhoneDial
import kotlinx.android.synthetic.main.need_help_fragment.*
import kotlinx.android.synthetic.main.support_dialog.contactSupport

class NeedHelpCCFragment : Fragment(R.layout.need_help_fragment) {

    companion object {
        var NAVIGATE_UP = false
    }

    override fun onResume() {
        super.onResume()
        if (NAVIGATE_UP) {
            NAVIGATE_UP = false
            findNavController().popBackStack()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        contactSupport.movementMethod = LinkMovementMethod.getInstance()
        @SuppressLint("SetTextI18n")
        contactSupport.text = "{${"800 91 24 91"}}"
            .coloredClickable(
                color = requireContext().getColorCompat(R.color.colorPrimary),
                bold = true
            ) {
                startPhoneDial("800 91 24 91")
            }

        navigationIcon.setSafeOnClickListener { findNavController().popBackStack() }

        next.setSafeOnClickListener {
            val action =
                DataUploadDirections.actionUploadData(
                    listOf(
                        getString(R.string.upload_data_warning_message_cc),
                        getString(R.string.upload_data_code_message_cc)
                    ).toTypedArray(),
                    true
                )
            findNavController().navigate(action)
        }
    }
}
