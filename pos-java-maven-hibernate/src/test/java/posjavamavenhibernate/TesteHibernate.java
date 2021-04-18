package posjavamavenhibernate;

import java.util.List;

import org.junit.Test;

import dao.DaoGeneric;
import model.TelefoneUser;
import model.UsuarioPessoa;

public class TesteHibernate {

	@Test
	public void testeHibernateUtil() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

		UsuarioPessoa pessoa = new UsuarioPessoa();

		pessoa.setIdade(21);
		pessoa.setLogin("teste");
		pessoa.setNome("Mayara Kelma");
		pessoa.setSenha("123");
		pessoa.setSobrenome("Pereira");
		pessoa.setEmail("arthur@curso.com.br");

		daoGeneric.salvar(pessoa);
	}

	@Test
	public void testeBuscar() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

		UsuarioPessoa pessoa = new UsuarioPessoa();
		pessoa.setId(3L);

		pessoa = daoGeneric.pesquisar(pessoa);

		System.out.println(pessoa);

	}

	@Test
	public void testeBuscar2() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

		UsuarioPessoa pessoa = daoGeneric.pesquisar(2L, UsuarioPessoa.class);

		System.out.println(pessoa);

	}

	@Test
	public void testeUpdate() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

		UsuarioPessoa pessoa = daoGeneric.pesquisar(2L, UsuarioPessoa.class);

		pessoa.setIdade(99);
		pessoa.setNome("Mudou hehehe");
		pessoa.setSenha("kd minha senha rapais");
		pessoa = daoGeneric.updateMerge(pessoa);

		System.out.println(pessoa);

	}

	@Test
	public void testeDelete() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

		UsuarioPessoa pessoa = daoGeneric.pesquisar(2L, UsuarioPessoa.class);
		daoGeneric.deletarPorId(pessoa);
	}

	@Test
	public void testeConsultar() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

		List<UsuarioPessoa> lista = daoGeneric.listar(UsuarioPessoa.class);

		for (UsuarioPessoa usuarioPessoa : lista) {
			System.out.println(usuarioPessoa);
			System.out.println("-----------------------------");
		}

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testeQueryList() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> list = daoGeneric.getEntityManager()
				.createQuery("from UsuarioPessoa where nome = 'Arthur Silva'").getResultList();

		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testeQueryListMaxResult() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createQuery("from UsuarioPessoa order by id ")
				.setMaxResults(1).getResultList();

		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testeQueryListParameter() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> lista = daoGeneric.getEntityManager().createQuery("from UsuarioPessoa where nome = :nompe and sobrenome = :sobre")
				.setParameter("nompe", "Beatriz Pinho")
				.setParameter("sobre", "Pereira")
				.getResultList();
		
		for (UsuarioPessoa usuarioPessoa : lista) {
			System.out.println(usuarioPessoa);
		}
	}

	@Test
	public void testeQuerySomaIdade() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		Long somaIdade = (Long) daoGeneric.getEntityManager()
				.createQuery(" select sum(u.idade) from UsuarioPessoa u ").getSingleResult();
		
		System.out.println("Soma das idades: " + somaIdade);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testeNameQuery1(){
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> lista = daoGeneric.getEntityManager().createNamedQuery("UsuarioPessoa.findAll").getResultList();
		
		for (UsuarioPessoa usuarioPessoa : lista) {
			System.out.println(usuarioPessoa);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testeNameQuery2(){
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> lista = daoGeneric.getEntityManager().createNamedQuery("UsuarioPessoa.buscaPorNome")
				.setParameter("nome", "Maria José")
				.getResultList();
		
		for (UsuarioPessoa usuarioPessoa : lista) {
			System.out.println(usuarioPessoa);
		}
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void testeGravaTelefone() {
		DaoGeneric daoGeneric = new DaoGeneric ();
		
		UsuarioPessoa pessoa = (UsuarioPessoa) daoGeneric.pesquisar(3L, UsuarioPessoa.class);
		TelefoneUser telefoneUser = new TelefoneUser();
		telefoneUser.setTipo("Casa");
		telefoneUser.setNumero("71 3635-1262");
		telefoneUser.setUsuarioPessoa(pessoa);
		
		daoGeneric.salvar(telefoneUser);
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void testeConsultaTelefones() {
		DaoGeneric daoGeneric = new DaoGeneric ();
		
		UsuarioPessoa pessoa = (UsuarioPessoa) daoGeneric.pesquisar(1L, UsuarioPessoa.class);

		for (TelefoneUser telefone : pessoa.getTelefoneUsers()) {
			System.out.println("Nome : "+ telefone.getUsuarioPessoa().getNome()  + " - Telefone: " + telefone.getNumero());
		}
	}

}
