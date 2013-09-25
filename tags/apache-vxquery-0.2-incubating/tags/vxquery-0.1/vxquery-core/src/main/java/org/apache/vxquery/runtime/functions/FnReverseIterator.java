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
package org.apache.vxquery.runtime.functions;

import java.util.Stack;

import org.apache.vxquery.context.StaticContext;
import org.apache.vxquery.exceptions.SystemException;
import org.apache.vxquery.functions.Function;
import org.apache.vxquery.runtime.CallStackFrame;
import org.apache.vxquery.runtime.LocalRegisterAccessor;
import org.apache.vxquery.runtime.RegisterAllocator;
import org.apache.vxquery.runtime.base.AbstractLazilyEvaluatedFunctionIterator;
import org.apache.vxquery.runtime.base.RuntimeIterator;

public class FnReverseIterator extends AbstractLazilyEvaluatedFunctionIterator {
    private final LocalRegisterAccessor<Stack<Object>> stack;

    public FnReverseIterator(RegisterAllocator rAllocator, Function fn, RuntimeIterator[] arguments,
            StaticContext ctx) {
        super(rAllocator, fn, arguments, ctx);
        stack = new LocalRegisterAccessor<Stack<Object>>(rAllocator.allocate(1));
    }

    @Override
    public void close(CallStackFrame frame) {
        arguments[0].close(frame);
    }

    @Override
    public Object next(CallStackFrame frame) throws SystemException {
        if (stack.get(frame) == null) {
            Stack<Object> s = new Stack<Object>();
            Object o;
            while ((o = arguments[0].next(frame)) != null) {
                s.push(o);
            }
            stack.set(frame, s);
        }
        Stack<Object> s = stack.get(frame);
        if (!s.isEmpty()) {
            return s.pop();
        }
        return null;
    }

    @Override
    public void open(CallStackFrame frame) {
        arguments[0].open(frame);
        stack.set(frame, null);
    }
}