/*
 * Copyright (C) 2014 Avram Lyon (ajlyon@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.myapplication.model;

import java.util.Map;

public class Item implements Versioned, Identifiable {
    private String key;
    private int version;

    private Map<String, Link> links;

    private Meta meta;
    private ItemData data;


    public String getKey() {
        return key;
    }

    public Map<String, Link> getLinks() {
        return links;
    }

    public Meta getMeta() {
        return meta;
    }

    public ItemData getData() {
        return data;
    }

    @Override
    public String getIdentifier() {
        return key;
    }

    @Override
    public int getVersion() {
        return version;
    }
}
