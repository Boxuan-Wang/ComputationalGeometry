package IDefinition;

import javax.naming.OperationNotSupportedException;

public interface IVector {
    boolean parallel(IVector vector);
    double dotProduct(IVector vector);

    IVector uniform() throws OperationNotSupportedException;
    double modular();

    IVector add(IVector vector);
    IVector multiply(double a);
}
