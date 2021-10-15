package tmDataNew;

public class DF_VariableLen  extends DescrField {
	byte view;	// byte / bit
	byte countVew;	// count byte / bit

	public void Print()
	{
		super.Print();
		System.out.println("view - " + view + " countView - " + countVew + '\n');
	}
}
