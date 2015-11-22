package com.example.lemmed;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	// Instancia do BD
	SQLiteDatabase bancoDados;
	
	//Variável do tipo Cursor para Utilização nas Consultas (PONTEIRO)
	Cursor resultadoConsultaSQL;
	
	//Definição da Variável do Tipo SPinner para Forma Remédio
	private static final String[] FormaRemedio ={"Selecione","Comprimido","Cápsula","Líquido","Pomada","Pasta","Colírio","Outros"};

	//Definição da Variável do Tipo SPinner para Tipo Remédio
	private static final String[] TipoRemedio ={"Selecione","Analgésicos","Anti-Térmicos","Anti_Estamínicos","Anti-Inflamatórios","Antibióticos","Antigripais","antifungicos","Outros"};

	
	//Adaptador que transforma as Strings em Array, em elementos Spinner
	ArrayAdapter<String> aFormaRemedio;
	ArrayAdapter<String> aTipoRemedio;
	
	//Cria Spinner
	Spinner spnFormaRemedio;
	Spinner spnTipoRemedio;
	
	//Variável para Spinners
	int idSpinnerFormaRemedio;
	int idSpinnerTipoRemedio;
	
	//Criando as Variáveis que irão armazenar o conteúdo da tela de cadatro
	//na Tabela Paciente
	EditText edNomePaciente, edIdadePaciente, edLocalizarPaciente;
	
	//Criando as Variáveis que irão armazenar o conteúdo da tela de cadatro
	//na Tabela Remédio
	EditText edNomeRemedio, edTipoRemedio,edFormaRemedio,edApresentacaoRemedio, edLocalizarRemedio;

	
	//Usar na Listagem
	TextView txtCodigo, txtDestino, txtTotalViagem;
	
	
	//Outras Variáveis
	int numreq, pos;


	
	
		
 /********************** Para exibir mensages: Com Alerta e Totast *******************/
	
	//Mostra Menssagem
	public void showMessage(String mensagem, String titulo){
	    
	    AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);
	    
	    dialogo.setTitle(titulo);
	    dialogo.setMessage(mensagem);
	    dialogo.setNeutralButton("OK", null);
	    dialogo.setIcon(R.drawable.info);
	    dialogo.show();
	}
		
	
	//Mostra Menssagem com Confirmação
	public void showMessageConfirma(String mensagem){

		Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_LONG).show();
	}
	

	
 /************************ Métodos para Chamada de Telas *****************************/
	
	//Chama Tela Principal (Main)
	public void CarregarMainActivit(){
		try{
		//Informa a Tela que irá aparecer
			setContentView(R.layout.activity_main);
		
		//Informa os controles que estarão presentes e associa aos criados em XML
			Button Main_btPaciente = (Button) findViewById(R.id.Main_btPaciente);
			Button Main_btRemedios = (Button) findViewById(R.id.Main_btRemedios);
			Button Main_btSobre = (Button) findViewById(R.id.Main_btSobre);
			Button Main_btAjuda = (Button) findViewById(R.id.Main_btAjuda);
			Button Main_btSair = (Button) findViewById(R.id.Main_btSair);
			
			
		
		}catch (Exception e){
			  showMessage(e.toString(),"Erro Chamar a Tela Principal");
		   }
			
	}	
	
	//Chama Tela Paciente
	public void CarregarPacienteActivit(){
		try{
		setContentView(R.layout.activity_paciente); 
		
		
		//Informa os controles que estarão presentes e associa aos criados em XML
		
		//Botoes
		Button Paciente_btLocalizar = (Button) findViewById(R.id.Paciente_btLoc);
		Button Paciente_btNovo = (Button) findViewById(R.id.Paciente_btNovo);
		Button Paciente_btSalvar = (Button) findViewById(R.id.Paciente_btSalvar);
		Button Paciente_btExcluir = (Button) findViewById(R.id.Paciente_btExcluir);
		Button Paciente_btOk = (Button) findViewById(R.id.Paciente_btOk);
		Button Paciente_btSair = (Button) findViewById(R.id.Paciente_btSair);
		
		//EditText
		EditText Paciente_edNomePaciente = (EditText)findViewById(R.id.Paciente_edNomePaciente);
		EditText Paciente_edIdadePaciente = (EditText)findViewById(R.id.Paciente_edIdade);
		
		
		}catch (Exception e){
			  showMessage(e.toString(),"Erro Chamar a Tela Pacientes");
		   }
		
	}

	//Chama Tela Sobre
	public void CarregarSobreActivit(){
		try{		
		setContentView(R.layout.activity_sobre);
		
		//Informa os controles que estarão presentes e associa aos criados em XML
		
		//Botoes
		Button Sobre_btSair = (Button) findViewById(R.id.Sobre_btSair);
		
		}catch (Exception e){
			  showMessage(e.toString(),"Erro Chamar a Tela Sobre");
		   }
				
	}
	
	//Chama Tela Ajuda
	public void CarregarAjudaActivit(){
		try{		
		setContentView(R.layout.activity_ajuda);
		
		//Informa os controles que estarão presentes e associa aos criados em XML
		
		//Botoes
		Button Ajuda_btSair = (Button) findViewById(R.id.Ajuda_btSair);
		
		
		
		}catch (Exception e){
			  showMessage(e.toString(),"Erro Chamar a Tela Ajuda");
		   }
		
	}
	
	//Chama Tela Sair
		public void CarregarSairActivit(){
			try{			
			setContentView(R.layout.activity_sair);
			
			//Informa os controles que estarão presentes e associa aos criados em XML
			
			//Botoes
			Button Sair_btOk = (Button) findViewById(R.id.Sair_btOk);		
			Button Sair_btCancela = (Button) findViewById(R.id.Sair_btCancela);
			
			
			}catch (Exception e){
				  showMessage(e.toString(),"Erro Chamar a Tela Sair");
			   }
			
		}
		
	//Chama Tela Remedio
	public void CarregarRemedioActivit(){
		try{			
		setContentView(R.layout.activity_remedio);
		
		//Informa os controles que estarão presentes e associa aos criados em XML
		
		
		//EditText
		EditText Remedio_edNomeRemedio = (EditText)findViewById(R.id.Remedio_edNomeRemedio);
		EditText Remedio_edTipo = (EditText)findViewById(R.id.Remedio_edTipo);
		EditText Remedio_edForma = (EditText)findViewById(R.id.Remedio_edForma);
		EditText Remedio_edApresentacao = (EditText)findViewById(R.id.Remedio_edApresentacao);
		EditText Remedio_edId = (EditText)findViewById(R.id.Remedio_edId);
		
		
		//Botoes
		Button Remedio_btLocalizar = (Button) findViewById(R.id.Remedio_btLoc);
		Button Remedio_btNovo = (Button) findViewById(R.id.Remedio_btNovo);
		Button Remedio_btSalvar = (Button) findViewById(R.id.Remedio_btSalvar);
		Button Remedio_btExcluir = (Button) findViewById(R.id.Remedio_btExcluir);
		Button Remedio_btOk = (Button) findViewById(R.id.Remedio_btOk);
		Button Remedio_btSair = (Button) findViewById(R.id.Remedio_btSair);
		
		}catch (Exception e){
			  showMessage(e.toString(),"Erro Chamar a Tela Remédios");
		   }
		
	}
	
	//Chama Tela Medicação
	public void CarregarMedicacaoActivit(){
		try{		
		setContentView(R.layout.activity_medicacao);
		
		
		
		
		//Botoes
		Button Medicacao_btLocalizar = (Button) findViewById(R.id.Medicacao_btLoc);
		Button Medicacao_btNovo = (Button) findViewById(R.id.Medicacao_btNovo);
		Button Medicacao_btSalvar = (Button) findViewById(R.id.Medicacao_btSalvar);
		Button Medicacao_btExcluir = (Button) findViewById(R.id.Medicacao_btExcluir);
		Button Medicacao_btOk = (Button) findViewById(R.id.Medicacao_btOk);
		Button Medicacao_btSair = (Button) findViewById(R.id.Medicacao_btSair);
		
		
		
		}catch (Exception e){
			  showMessage(e.toString(),"Erro Chamar a Tela Medicação");
		   }
		
	}
	

/************************ Métodos para Criar o Banco e as Entidades e Atributos *****************************/
	
	
	//Cria o Banco de Dados após ser Estanciado no Inicio do Código
	public void CriarBanco(){
		try{
			
			//Criar o Banco Já Estanciado no Inicio do Código
			bancoDados = openOrCreateDatabase("BDLemMed", Context.MODE_PRIVATE, null);
			
		   }catch (Exception e){
			  showMessage(e.toString(),"Erro ao Criar o Banco de Dados LemMed");
		   }
		
		
		try{
			//Criar a Entidade de Pacientes
		    bancoDados.execSQL("CREATE TABLE IF NOT EXISTS Tbl_Pacientes"+
							"( IdPaciente INTEGER PRIMARY KEY AUTOINCREMENT, "+
							" NomePaciente char(40 NOT NULL,"+
							" Idade int(1) NOT NULL, "+
							" tipo_viagem char(10 NOT NULL,"+
							" data_viagem VARCHAR(10),"+
							" numero_pessoa int, "+
							" total_viagem REAL, "+
							" viagem_realizada char(1) );");

		   }catch (Exception e){
			  showMessage(e.toString(),"Erro ao Criar a Tabela Pacientes");
		   }
		
		try{
			//Criar a Entidade de Remedios (Medicamentos)
		    bancoDados.execSQL("CREATE TABLE IF NOT EXISTS Tbl_Remedios"+
							"( IdRemedio INTEGER PRIMARY KEY AUTOINCREMENT, "+
							" NomeRemedio char(30 NOT NULL,"+
							" TipoRemedio char(20 NOT NULL,"+
							" FormaRemedio char(20),"+
							" ApresentacaoRemedio char(20) );");

		   }catch (Exception e){
			  showMessage(e.toString(),"Erro ao Criar a Tabela Remedios");
		   }
		
		
	}
	
	
/************************ Métodos para Manipular as Tabelas(Entidades) e Atributos *****************************/

//Entidade Pacientes
	public void limpaCampos_Paciente(){
	

	
	}
	
	public void cadastrarPaciente(View v){
		
		try{
			edNomePaciente		= (EditText) findViewById(R.id.Paciente_edNomePaciente);
			edIdadePaciente 	= (EditText) findViewById(R.id.Paciente_edIdade);

			

			//executa a SQL de insert na Entidade tbl_Pacientes			
			bancoDados.execSQL("INSERT INTO tbl_Pacientes (NomePaciente,IdadePaciente)"+
					   " VALUES('"+edNomePaciente.getText().toString()				 +
					   "','"+ edIdadePaciente.getText().toString() 					 +
					   "')");
				
			//Exibe a mensagem que Cadastrou com sucesso
			showMessageConfirma("LemMed - Cadastro realizado com sucesso.");
				
			//Limpa TODOS os campos
			limpaCampos_Paciente();
				
		}catch	(Exception e){
			showMessage("Erro ao cadastrar","Erro");
			showMessage(e.toString(),"Erro");
			 
		}//fecha o catch	
	}//fecha o metodo cadastrar	

	public void editarPaciente(View v){
	

}

	public void excluirPaciente(View v){
	
	}

	public void localizarPaciente(View v){
		
		
	}
	
//Entidade Remedios
	public void limparCampos_Remedios(){
		
		
	}

	public void cadastrarRemedio(View v){
		
		try{
			
			edNomeRemedio =(EditText) findViewById(R.id.Remedio_edNomeRemedio);
			edTipoRemedio =(EditText) findViewById(R.id.Remedio_edTipo);
			edFormaRemedio =(EditText) findViewById(R.id.Remedio_edForma);
			edApresentacaoRemedio =(EditText) findViewById(R.id.Remedio_edApresentacao);
			edLocalizarRemedio =(EditText) findViewById(R.id.Paciente_edLocalizar);


			//executa a SQL de insert na Entidade tbl_Pacientes			
			bancoDados.execSQL("INSERT INTO tbl_Remedios (NomeRemedio,TipoRemedio,FormaRemedio,ApresentacaoRemedio)"+
					   " VALUES('"+edNomeRemedio.getText().toString()				 +
					   "','"+ edTipoRemedio.getText().toString() 					 +
					   "','"+ edFormaRemedio.getText().toString() 					 +
					   "','"+ edApresentacaoRemedio.getText().toString() 			 +
					   "')");
				
			//Exibe a mensagem que Cadastrou com sucesso
			showMessageConfirma("LemMed - Cadastro realizado com sucesso.");
				
			//Limpa TODOS os campos
			limpaCampos_Paciente();
				
		}catch	(Exception e){
			showMessage("Erro ao cadastrar Remédio","Erro");
			showMessage(e.toString(),"Erro");
			 
		}//fecha o catch	
		
	}
	
	
/************************ Navegação entre Activits *****************************/	
	

public void botaoPaciente(View v){
	CarregarPacienteActivit();
}

public void botaoRemedio(View v){
	CarregarRemedioActivit();
}

public void botaoMedicacao(View v){
	CarregarMedicacaoActivit();
}

public void botaoSobre(View v){
	CarregarSobreActivit();
}

public void botaoAjuda(View v){
	CarregarAjudaActivit();
}
	
public void botaoSair(View v){
	CarregarSairActivit();
}

public void botaoVoltar(View v){
	CarregarMainActivit();
}

public void botaoSairCancela(View v){
	CarregarMainActivit();
}

public void botaoSairOk(View v){

		Intent intent = new Intent(getApplicationContext(), Activity.class);
	    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	    intent.putExtra("EXIT", true);
	    startActivity(intent);
}



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		CriarBanco();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
