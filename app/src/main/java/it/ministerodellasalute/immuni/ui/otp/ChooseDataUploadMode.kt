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

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import it.ministerodellasalute.immuni.R
import it.ministerodellasalute.immuni.SettingsNavDirections
import it.ministerodellasalute.immuni.extensions.activity.setLightStatusBar
import it.ministerodellasalute.immuni.extensions.view.setSafeOnClickListener
import kotlinx.android.synthetic.main.choose_data_upload_mode.*

class ChooseDataUploadMode : Fragment(R.layout.choose_data_upload_mode) {

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
        (activity as? AppCompatActivity)?.setLightStatusBar(resources.getColor(R.color.background_darker))

        navigationIcon.setSafeOnClickListener {
            findNavController().popBackStack()
        }

        nextOS.setSafeOnClickListener {
            val action =
                SettingsNavDirections.actionUploadData(
                    listOf(
                        getString(R.string.upload_data_warning_message),
                        getString(R.string.upload_data_code_message)
                    ).toTypedArray(),
                    false
                )
            findNavController().navigate(action)
        }

        nextCC.setSafeOnClickListener {
            val action =
                SettingsNavDirections.actionNeedHelpUploadCc()
            findNavController().navigate(action)
        }
    }
}
