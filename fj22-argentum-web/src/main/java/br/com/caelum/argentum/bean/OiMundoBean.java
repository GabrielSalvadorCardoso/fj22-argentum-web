package br.com.caelum.argentum.bean;

import javax.faces.bean.ManagedBean;

//Uma classe ManagedBean tem a responsabilidade de se comunicar com JSF através de seus getters e setters
@ManagedBean
public class OiMundoBean {
	private String mensagem = "Quem é você?";
	private String nome;
	
	public String getMensagem() {
		System.out.println("getMensagem()");
		return this.mensagem;
	}
	public String getNome() {
		System.out.println("getNome()");
		return this.nome;
	}
	public void setNome(String nome) {
		System.out.println("setNome()");
		this.nome = nome;
	}
	public void nomeFoiDigitado(){
		System.out.println("Nome: " + this.nome);
	}
}
