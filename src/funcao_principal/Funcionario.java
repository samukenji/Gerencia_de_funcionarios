package funcao_principal;

public class Funcionario {

	private String nome;
	private String email;
	private Double salario;
	
	//CONSTRUTOR
	public Funcionario(String nome, String email, Double salario)
	{
		this.nome= nome;
		this.email= email;
		this.salario=salario;
	}

	//GETTERS E SETTERS
	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	//TOSTRING()
	@Override
	public String toString() {
		return  nome + "," +  email  + "," + salario;
	}
	
}
