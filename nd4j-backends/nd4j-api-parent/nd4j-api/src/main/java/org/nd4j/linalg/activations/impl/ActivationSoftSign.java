package org.nd4j.linalg.activations.impl;

import org.apache.commons.math3.util.Pair;
import org.nd4j.linalg.activations.IActivation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.ops.impl.transforms.Sigmoid;
import org.nd4j.linalg.api.ops.impl.transforms.SigmoidDerivative;
import org.nd4j.linalg.api.ops.impl.transforms.SoftSign;
import org.nd4j.linalg.api.ops.impl.transforms.SoftSignDerivative;
import org.nd4j.linalg.factory.Nd4j;

/**
 * Created by susaneraly on 12/17/16.
 */
public class ActivationSoftSign implements IActivation {

    @Override
    public INDArray getActivation(INDArray in, boolean training) {
        Nd4j.getExecutioner().execAndReturn(new SoftSign(in));
        return in;
    }

    @Override
    public INDArray getGradient(INDArray in) {
        Nd4j.getExecutioner().execAndReturn(new SoftSignDerivative(in));
        return in;
    }

    @Override
    public Pair<INDArray, INDArray> getActivationAndGradient(INDArray in) {
        INDArray activation = in.dup();
        INDArray gradient = in.dup();
        getActivation(activation, true);
        getGradient(gradient);
        return new Pair<INDArray, INDArray>(activation,gradient);
    }

    @Override
    public String toString() {
        return "softsign";
    }
}