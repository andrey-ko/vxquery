/*
* Licensed to the Apache Software Foundation (ASF) under one or more
* contributor license agreements.  See the NOTICE file distributed with
* this work for additional information regarding copyright ownership.
* The ASF licenses this file to You under the Apache License, Version 2.0
* (the "License"); you may not use this file except in compliance with
* the License.  You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package org.apache.vxquery.types;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class SequenceType {
    private static final Map<ItemType, SequenceType[]> BUILTIN_SEQ_TYPES;

    private ItemType itemType;
    private Quantifier quantifier;

    static {
        Map<ItemType, SequenceType[]> types = new HashMap<ItemType, SequenceType[]>();

        createBuiltinEntry(types, BuiltinTypeRegistry.XS_ANY_ATOMIC);
        createBuiltinEntry(types, BuiltinTypeRegistry.XS_STRING);
        createBuiltinEntry(types, BuiltinTypeRegistry.XS_NORMALIZED_STRING);
        createBuiltinEntry(types, BuiltinTypeRegistry.XS_TOKEN);
        createBuiltinEntry(types, BuiltinTypeRegistry.XS_LANGUAGE);
        createBuiltinEntry(types, BuiltinTypeRegistry.XS_NMTOKEN);
        createBuiltinEntry(types, BuiltinTypeRegistry.XS_NAME);
        createBuiltinEntry(types, BuiltinTypeRegistry.XS_NCNAME);
        createBuiltinEntry(types, BuiltinTypeRegistry.XS_ID);
        createBuiltinEntry(types, BuiltinTypeRegistry.XS_IDREF);
        createBuiltinEntry(types, BuiltinTypeRegistry.XS_ENTITY);
        createBuiltinEntry(types, BuiltinTypeRegistry.XS_UNTYPED_ATOMIC);
        createBuiltinEntry(types, BuiltinTypeRegistry.XS_DATETIME);
        createBuiltinEntry(types, BuiltinTypeRegistry.XS_DATE);
        createBuiltinEntry(types, BuiltinTypeRegistry.XS_TIME);
        createBuiltinEntry(types, BuiltinTypeRegistry.XS_DURATION);
        createBuiltinEntry(types, BuiltinTypeRegistry.XS_YEAR_MONTH_DURATION);
        createBuiltinEntry(types, BuiltinTypeRegistry.XS_DAY_TIME_DURATION);
        createBuiltinEntry(types, BuiltinTypeRegistry.XSEXT_NUMERIC);
        createBuiltinEntry(types, BuiltinTypeRegistry.XS_FLOAT);
        createBuiltinEntry(types, BuiltinTypeRegistry.XS_DOUBLE);
        createBuiltinEntry(types, BuiltinTypeRegistry.XS_DECIMAL);
        createBuiltinEntry(types, BuiltinTypeRegistry.XS_INTEGER);
        createBuiltinEntry(types, BuiltinTypeRegistry.XS_NON_POSITIVE_INTEGER);
        createBuiltinEntry(types, BuiltinTypeRegistry.XS_NEGATIVE_INTEGER);
        createBuiltinEntry(types, BuiltinTypeRegistry.XS_LONG);
        createBuiltinEntry(types, BuiltinTypeRegistry.XS_LONG);
        createBuiltinEntry(types, BuiltinTypeRegistry.XS_INT);
        createBuiltinEntry(types, BuiltinTypeRegistry.XS_SHORT);
        createBuiltinEntry(types, BuiltinTypeRegistry.XS_BYTE);
        createBuiltinEntry(types, BuiltinTypeRegistry.XS_NON_NEGATIVE_INTEGER);
        createBuiltinEntry(types, BuiltinTypeRegistry.XS_UNSIGNED_LONG);
        createBuiltinEntry(types, BuiltinTypeRegistry.XS_UNSIGNED_INT);
        createBuiltinEntry(types, BuiltinTypeRegistry.XS_UNSIGNED_SHORT);
        createBuiltinEntry(types, BuiltinTypeRegistry.XS_UNSIGNED_BYTE);
        createBuiltinEntry(types, BuiltinTypeRegistry.XS_POSITIVE_INTEGER);
        createBuiltinEntry(types, BuiltinTypeRegistry.XS_G_YEAR_MONTH);
        createBuiltinEntry(types, BuiltinTypeRegistry.XS_G_YEAR);
        createBuiltinEntry(types, BuiltinTypeRegistry.XS_G_MONTH_DAY);
        createBuiltinEntry(types, BuiltinTypeRegistry.XS_G_DAY);
        createBuiltinEntry(types, BuiltinTypeRegistry.XS_G_MONTH);
        createBuiltinEntry(types, BuiltinTypeRegistry.XS_BOOLEAN);
        createBuiltinEntry(types, BuiltinTypeRegistry.XS_BASE64_BINARY);
        createBuiltinEntry(types, BuiltinTypeRegistry.XS_HEX_BINARY);
        createBuiltinEntry(types, BuiltinTypeRegistry.XS_ANY_URI);
        createBuiltinEntry(types, BuiltinTypeRegistry.XS_QNAME);
        createBuiltinEntry(types, BuiltinTypeRegistry.XS_NOTATION);

        createBuiltinEntry(types, AnyItemType.INSTANCE);
        createBuiltinEntry(types, AnyNodeType.INSTANCE);
        createBuiltinEntry(types, DocumentType.ANYDOCUMENT);
        createBuiltinEntry(types, ElementType.ANYELEMENT);
        createBuiltinEntry(types, AttributeType.ANYATTRIBUTE);
        createBuiltinEntry(types, CommentType.INSTANCE);
        createBuiltinEntry(types, ProcessingInstructionType.ANYPI);

        BUILTIN_SEQ_TYPES = Collections.unmodifiableMap(types);
    }

    private static void createBuiltinEntry(Map<ItemType, SequenceType[]> types, ItemType itemType) {
        types
                .put(itemType, new SequenceType[] { new SequenceType(itemType, Quantifier.QUANT_ZERO),
                        new SequenceType(itemType, Quantifier.QUANT_ONE),
                        new SequenceType(itemType, Quantifier.QUANT_QUESTION),
                        new SequenceType(itemType, Quantifier.QUANT_STAR),
                        new SequenceType(itemType, Quantifier.QUANT_PLUS), });
    }

    public static SequenceType create(ItemType itemType, Quantifier quantifier) {
        SequenceType[] types = BUILTIN_SEQ_TYPES.get(itemType);
        if (types == null) {
            return new SequenceType(itemType, quantifier);
        }
        return types[quantifier.ordinal()];
    }

    private SequenceType(ItemType itemType, Quantifier quantifier) {
        this.itemType = itemType;
        this.quantifier = quantifier;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public Quantifier getQuantifier() {
        return quantifier;
    }

    public XQType toXQType() {
        return TypeOperations.quantified(itemType, quantifier);
    }

    public String toString() {
        return String.valueOf(itemType) + " " + quantifier;
    }
}