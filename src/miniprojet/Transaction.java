package miniprojet;

public class Transaction {
	public String name;
	public TypeTransaction type;
	public Double value;

	Transaction(String name, TypeTransaction type, Double value) {
		this.name = name;
		this.type = type;
		this.value = value;
	}
}