/*******************************************************************************
 * Copyright (c) 2018 EclipseSource.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 ******************************************************************************/
package com.eclipsesource.json;

import java.io.IOException;
import java.io.Writer;

/**
 * @see JsonAware
 */
public class JsonDynamicValue extends JsonValue
{
  private final JsonAware dynamicValue;

  private JsonValue wrappedValue;
  private long revision = 0;

  JsonDynamicValue(final JsonAware dynamicValue)
  {
    this.dynamicValue = dynamicValue;
  }

  @Override
  public boolean isDynamicValue(final Class<? extends JsonAware> jaCls)
  {
    return jaCls.isInstance(dynamicValue);
  }

  @Override
  public <JA extends JsonAware> JA getDynamicValue(final Class<JA> jaCls)
  {
    if (jaCls.isInstance(dynamicValue)) {
      return jaCls.cast(dynamicValue);
    }
    throw new UnsupportedOperationException("Not the correct JsonAware subclass, requested: " + jaCls.getName() + " found: " + dynamicValue.getClass().getName());
  }

  @Override
  public boolean isObject() {
    return getWrappedValue().isObject();
  }

  @Override
  public boolean isArray() {
    return getWrappedValue().isArray();
  }

  @Override
  public boolean isNumber() {
    return getWrappedValue().isNumber();
  }

  @Override
  public boolean isString() {
    return getWrappedValue().isString();
  }

  @Override
  public boolean isBoolean() {
    return getWrappedValue().isBoolean();
  }

  @Override
  public boolean isTrue() {
    return getWrappedValue().isTrue();
  }

  @Override
  public boolean isFalse() {
    return getWrappedValue().isFalse();
  }

  @Override
  public boolean isNull() {
    return getWrappedValue().isNull();
  }

  @Override
  public JsonObject asObject() {
    return getWrappedValue().asObject();
  }

  @Override
  public JsonArray asArray() {
    return getWrappedValue().asArray();
  }

  @Override
  public int asInt() {
    return getWrappedValue().asInt();
  }

  @Override
  public long asLong() {
    return getWrappedValue().asLong();
  }

  @Override
  public float asFloat() {
    return getWrappedValue().asFloat();
  }

  @Override
  public double asDouble() {
    return getWrappedValue().asDouble();
  }

  @Override
  public String asString() {
    return getWrappedValue().asString();
  }

  @Override
  public boolean asBoolean() {
    return getWrappedValue().asBoolean();
  }

  @Override
  public void writeTo(Writer writer) throws IOException {
    getWrappedValue().writeTo(writer);
  }

  @Override
  public void writeTo(Writer writer, WriterConfig config) throws IOException {
    getWrappedValue().writeTo(writer, config);
  }

  @Override
  public String toString() {
    return getWrappedValue().toString();
  }

  @Override
  public String toString(WriterConfig config) {
    return getWrappedValue().toString(config);
  }

  @Override
  public boolean equals(Object object) {
    return getWrappedValue().equals(object);
  }

  @Override
  public int hashCode() {
    return getWrappedValue().hashCode();
  }

  @Override
  void write(JsonWriter writer) throws IOException
  {
    getWrappedValue().write(writer);
  }

  private JsonValue getWrappedValue()
  {
    if (wrappedValue == null || revision != dynamicValue.jsonRevision())
    {
      wrappedValue = dynamicValue.getJsonValue();
      revision = dynamicValue.jsonRevision();
    }
    return wrappedValue;
  }
}
