package org.apache.vxquery.runtime.functions.unary;

import java.io.DataOutput;
import java.io.IOException;

import org.apache.vxquery.datamodel.accessors.atomic.XSDecimalPointable;
import org.apache.vxquery.datamodel.values.ValueTag;
import org.apache.vxquery.exceptions.SystemException;

import edu.uci.ics.hyracks.data.std.primitive.DoublePointable;
import edu.uci.ics.hyracks.data.std.primitive.FloatPointable;
import edu.uci.ics.hyracks.data.std.primitive.LongPointable;

public class NumericUnaryMinusOperation extends AbstractNumericUnaryOperation {

    @Override
    public void operateDecimal(XSDecimalPointable decp, DataOutput dOut) throws SystemException, IOException {
        dOut.write(ValueTag.XS_DECIMAL_TAG);
        dOut.write(decp.getDecimalPlace());
        dOut.writeLong(-decp.getDecimalValue());
    }

    @Override
    public void operateDouble(DoublePointable doublep, DataOutput dOut) throws SystemException, IOException {
        dOut.write(ValueTag.XS_DOUBLE_TAG);
        dOut.writeDouble(-doublep.getDouble());
    }

    @Override
    public void operateFloat(FloatPointable floatp, DataOutput dOut) throws SystemException, IOException {
        dOut.write(ValueTag.XS_FLOAT_TAG);
        dOut.writeFloat(-floatp.getFloat());
    }

    @Override
    public void operateInteger(LongPointable longp, DataOutput dOut) throws SystemException, IOException {
        dOut.write(ValueTag.XS_INTEGER_TAG);
        dOut.writeLong(-longp.getLong());
    }
}