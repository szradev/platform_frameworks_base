/*
 * Copyright 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.media.tv.tuner.filter;

import android.annotation.NonNull;
import android.annotation.Nullable;
import android.annotation.RequiresPermission;
import android.annotation.SystemApi;
import android.content.Context;
import android.media.tv.tuner.TunerUtils;

/**
 * Filter configuration for a TS filter.
 *
 * @hide
 */
@SystemApi
public class TsFilterConfiguration extends FilterConfiguration {
    private final int mTpid;

    private TsFilterConfiguration(Settings settings, int tpid) {
        super(settings);
        mTpid = tpid;
    }

    @Override
    public int getType() {
        return FilterConfiguration.FILTER_TYPE_TS;
    }

    /**
     * Gets the {@link Settings} object of this filter configuration.
     */
    @Nullable
    public Settings getSettings() {
        return mSettings;
    }
    /**
     * Gets Tag Protocol ID.
     */
    public int getTpid() {
        return mTpid;
    }

    /**
     * Creates a builder for {@link TsFilterConfiguration}.
     *
     * @param context the context of the caller.
     */
    @RequiresPermission(android.Manifest.permission.ACCESS_TV_TUNER)
    @NonNull
    public static Builder builder(@NonNull Context context) {
        TunerUtils.checkTunerPermission(context);
        return new Builder();
    }

    /**
     * Builder for {@link TsFilterConfiguration}.
     */
    public static class Builder {
        private Settings mSettings;
        private int mTpid;

        private Builder() {
        }

        /**
         * Sets filter settings.
         *
         * @param settings the filter settings.
         */
        @NonNull
        public Builder setSettings(@NonNull Settings settings) {
            mSettings = settings;
            return this;
        }

        /**
         * Sets Tag Protocol ID.
         *
         * @param tpid the Tag Protocol ID.
         */
        @NonNull
        public Builder setTpid(int tpid) {
            mTpid = tpid;
            return this;
        }

        /**
         * Builds a {@link TsFilterConfiguration} object.
         */
        @NonNull
        public TsFilterConfiguration build() {
            return new TsFilterConfiguration(mSettings, mTpid);
        }
    }
}
