/*
 * This file is part of Sponge, licensed under the MIT License (MIT).
 *
 * Copyright (c) SpongePowered <https://www.spongepowered.org>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.spongepowered.common.item.inventory.property;

import com.flowpowered.math.vector.Vector2i;
import org.spongepowered.api.data.Property;
import org.spongepowered.api.item.inventory.property.SlotPos;
import org.spongepowered.api.util.Coerce;

public class SpongeSlotPos extends AbstractInventoryProperty<String, Vector2i> implements SlotPos {

    /**
     * Create a new SlotPos property for matching the specified value.
     *
     * @param value the value to match
     */
    public SpongeSlotPos(Vector2i value) {
        super(value);
    }

    /**
     * Create a new SlotPos property for matching the specified value.
     *
     * @param x slot x position
     * @param y slot y position
     */
    public SpongeSlotPos(int x, int y) {
        super(new Vector2i(x, y));
    }

    /**
     * Create a new SlotPos property for matching the specified value with the
     * specified operator.
     *
     * @param value the value to match
     * @param operator the operator to use when comparing with other properties
     */
    public SpongeSlotPos(Vector2i value, Operator operator) {
        super(value, operator);
    }

    /**
     * Create a new SlotPos property for matching the specified value with the
     * specified operator.
     *
     * @param x slot x position
     * @param y slot y position
     * @param operator the operator to use when comparing with other properties
     */
    public SpongeSlotPos(int x, int y, Operator operator) {
        super(new Vector2i(x, y), operator);
    }

    /**
     * Creates a new SlotPos property for matching the specified value with the
     * specified operator.
     *
     * @param value the value to match
     * @param operator the operator to use when comparing with other properties
     */
    public SpongeSlotPos(Object value, Operator operator) {
        super(Coerce.toVector2i(value), operator);
    }

    @Override
    public int getX() {
        return this.getValue().getX();
    }

    @Override
    public int getY() {
        return this.getValue().getY();
    }

    @Override
    public int compareTo(Property<?, ?> other) {
        if (other == null) {
            return 1;
        }

        return this.getValue().compareTo(Coerce.toVector2i(other.getValue()));
    }

    public static class Builder implements SlotPos.Builder {

        private Object value;
        private Operator operator;

        @Override
        public SlotPos.Builder value(final Object value) {
            this.value = value;
            return this;
        }

        @Override
        public SlotPos.Builder operator(final Operator operator) {
            this.operator = operator;
            return this;
        }

        @Override
        public SlotPos.Builder from(final SlotPos value) {
            this.value = value.getValue();
            this.operator = value.getOperator();
            return this;
        }

        @Override
        public SlotPos.Builder reset() {
            this.value = null;
            this.operator = null;
            return this;
        }

        @Override
        public SlotPos build() {
            return new SpongeSlotPos(this.value, this.operator);
        }
    }

}
