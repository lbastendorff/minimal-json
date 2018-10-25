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

/**
 * Implemented by complex types that wish to have dynamic data structures that can change right up to JSON serialisation time.<BR>
 * <BR>
 * Effectively this is just a wrapper implementation around {@link JsonValue} .
 */
public interface JsonAware
{
  /**
   * Changes in the value returned here trigger a re-serialisation of this Object.<BR>
   * Specifically this means that the {@link #getJsonValue()} function will be reevaluated by any function
   * of minimal-json which wishes to operate upon the real {@link JsonValue} that {@link JsonAware} is wrapping.<BR>
   * <BR>
   * If the serialised form of this {@link JsonAware} should never change this should return 0.<BR>
   * If the serialised form of this {@link JsonAware} should change arbitrarily then a counter should be returned and
   * other functions should increment when required.<BR>
   * If the serialised form of this {@link JsonAware} should change on every json-minimal operation return {@link System#nanoTime()}<BR>
   *
   * @return long revision counter
   */
  public long jsonRevision();

  /**
   * @return the real {@link JsonValue} of this complex object.
   */
  public JsonValue getJsonValue();
}
