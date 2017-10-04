/**
 * ProductID uniquely identifies a product.  Should be IMMUTABLE!!!
 */
 
 /**
 * @author Your Name Here
 * INFS 519
 * Fall 2016
 */
 
public class ProductID
{
    private final String epc;
    private final int serialNumber;
    
    public ProductID(String epc, int serialNumber)
    {
        this.epc = epc;
        this.serialNumber = serialNumber;
    }
    
    @Override
    public int hashCode()
    {
        int hash = 17; // pick prime constants
	hash = 31 * hash + epc.hashCode();
	hash = 31 * hash + ((Integer)serialNumber).hashCode();
	return hash; // MODIFY CODE
    }

    @Override
    public boolean equals(Object obj)
    {
        // 1. performance trick, typical to check super.equals also
	if(obj == this) 
	return true;
	// 2. type check, handles null
	if(!(obj instanceof ProductID)) 
	return false;
	// 3. safe cast so can check each attribute
	ProductID that = (ProductID)obj;
	// 4. check each attribute for equality, should handle nulls
	return (this.epc.equals(that.epc) && this.serialNumber == that.serialNumber); // MODIFY CODE
    }
    
    @Override
    public String toString()
    {
        return epc + ","+ serialNumber;
    }
}
