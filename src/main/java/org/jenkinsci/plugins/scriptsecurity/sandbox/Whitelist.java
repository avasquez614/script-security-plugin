/*
 * The MIT License
 *
 * Copyright 2014 CloudBees, Inc.
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

package org.jenkinsci.plugins.scriptsecurity.sandbox;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

/**
 * Determines which methods and similar members which scripts may call.
 */
public abstract class Whitelist {

    /**
     * Checks whether a given virtual method may be invoked.
     * <p>Note that {@code method} should not be implementing or overriding a method in a supertype;
     * in such a case the caller must pass that supertype method instead.
     * In other words, call site selection is the responsibility of the caller (such as {@code GroovySandbox}), not the whitelist.
     * @param method a method defined in the JVM
     * @param receiver {@code this}, the receiver of the method call
     * @param args zero or more arguments
     * @return true to allow the method to be called, false to reject it
     */
    public abstract boolean permitsMethod(@Nonnull Method method, @Nonnull Object receiver, @Nonnull Object[] args);

    public abstract boolean permitsConstructor(@Nonnull Constructor<?> constructor, @Nonnull Object[] args);

    public abstract boolean permitsStaticMethod(@Nonnull Method method, @Nonnull Object[] args);

    public abstract boolean permitsFieldGet(@Nonnull Field field, @Nonnull Object receiver);

    public abstract boolean permitsFieldSet(@Nonnull Field field, @Nonnull Object receiver, @CheckForNull Object value);

    public abstract boolean permitsStaticFieldGet(@Nonnull Field field);

    public abstract boolean permitsStaticFieldSet(@Nonnull Field field, @CheckForNull Object value);

}
