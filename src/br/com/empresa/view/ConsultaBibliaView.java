package br.com.empresa.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.table.TableColumnModel;

import br.com.empresa.exception.BOException;
import br.com.empresa.service.IServicoBeanLocal;
import br.com.empresa.service.ServicoBeanLocal;
import br.com.empresa.view.util.RowData;
import br.com.empresa.view.util.TableModel;
import br.com.empresa.vo.BibliaVO;
import br.com.empresa.vo.LivroVO;

public class ConsultaBibliaView extends JDialog {

	private final JPanel Panel = new JPanel();
	private IServicoBeanLocal servicoBeanLocal;
	private JTable table;
	private JTextField tfTexto;
	private TableModel tableModel;
	private JComboBox cbLivro;
	private JComboBox cbCapitulo;
	private JTextField tfDe;
	private JTextField tfAte;

	public ConsultaBibliaView() {
		
		servicoBeanLocal = new ServicoBeanLocal();

		
		setTitle("Consulta Biblia");
		setBounds(100, 100, 830, 457);
		getContentPane().setLayout(null);
		Panel.setBounds(10, 11, 143, 362);
		Panel.setBorder(null);
		getContentPane().add(Panel);
		Panel.setLayout(null);
		
		tfDe = new JTextField();
		tfDe.setColumns(10);
		tfDe.setBounds(476, 12, 58, 22);

		
		tfAte = new JTextField();
		tfAte.setColumns(10);
		tfAte.setBounds(564, 12, 58, 22);

		JLabel lblLivro = new JLabel("Livro");
		lblLivro.setBounds(52, 11, 34, 14);
		Panel.add(lblLivro);

		JLabel lblCapitulo = new JLabel("Capitulo");
		lblCapitulo.setBounds(39, 62, 60, 14);
		Panel.add(lblCapitulo);

		JLabel lblVersiculo = new JLabel("Versiculos:");
		lblVersiculo.setBounds(42, 124, 72, 14);
		Panel.add(lblVersiculo);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(163, 73, 641, 300);
		getContentPane().add(scrollPane);
			
		tableModel = new TableModel();
		tableModel.addColumn("Escritor");
		tableModel.addColumn("Capitulo");
		tableModel.addColumn("Versiculo");
		tableModel.addColumn("Texto");

		table = new JTable(tableModel);
		table.setAutoscrolls(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		TableColumnModel tableColumnModel = table.getColumnModel();
		tableColumnModel.getColumn(0).setPreferredWidth(100);
		tableColumnModel.getColumn(1).setPreferredWidth(100);
		tableColumnModel.getColumn(2).setPreferredWidth(100);
		tableColumnModel.getColumn(3).setPreferredWidth(330);


		scrollPane.setViewportView(table);

		cbLivro = new JComboBox();
		cbLivro.setBounds(10, 28, 119, 22);
		cbLivro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listarCapitulo();
			}
		});
		DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel<>();
		cbLivro.setModel(defaultComboBoxModel);
		defaultComboBoxModel.insertElementAt(null, 0);
		Panel.add(cbLivro);

		cbCapitulo = new JComboBox();
		cbCapitulo.setBounds(10, 84, 119, 22);
		Panel.add(cbCapitulo);
		
				JButton btnPesquisar = new JButton("Pesquisar");
				btnPesquisar.setBounds(10, 305, 119, 23);
				Panel.add(btnPesquisar);
				btnPesquisar.setBackground(UIManager.getColor("Button.light"));
				btnPesquisar.setForeground(Color.BLACK);
				
						JButton btnLimpar = new JButton("Limpar");
						btnLimpar.setBounds(10, 339, 119, 23);
						Panel.add(btnLimpar);
						
						tfDe = new JTextField();
						tfDe.setBounds(10, 155, 42, 20);
						Panel.add(tfDe);
						tfDe.setColumns(10);
						
						JLabel lblNewLabel = new JLabel("Até");
						lblNewLabel.setBounds(60, 158, 28, 14);
						Panel.add(lblNewLabel);
						
						tfAte = new JTextField();
						tfAte.setColumns(10);
						tfAte.setBounds(87, 155, 42, 20);
						Panel.add(tfAte);
						btnLimpar.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								limpar();
							}
						});
				btnPesquisar.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						pesquisar();
					}
				});
				
		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fecharJanela();
			}
		});
		btnFechar.setBounds(715, 384, 89, 23);
		getContentPane().add(btnFechar);
		
				JLabel lblTexto = new JLabel("Procurar por texto:");
				lblTexto.setBounds(163, 22, 143, 14);
				getContentPane().add(lblTexto);
				
						tfTexto = new JTextField();
						tfTexto.setBounds(163, 39, 641, 23);
						getContentPane().add(tfTexto);
						tfTexto.setColumns(10);
		
		//Coloca a tela no centro da janela.
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

		inicializarComponentes();

	}

	private void inicializarComponentes() {
		servicoBeanLocal = new ServicoBeanLocal();
		try {
			List<LivroVO>livros = servicoBeanLocal.listarLivros();
			for(LivroVO livroVO : livros) {
				cbLivro.addItem(livroVO);
			}
		}catch(BOException e) {
			e.printStackTrace();
		}
	}

	private void pesquisar() {
		

		
		TableModel tableModel = (TableModel) table.getModel();
		tableModel.clearTable();
		
		try {
		
			
			LivroVO livroSelecionado = null;
			
			if(cbLivro != null) {
			   livroSelecionado = (LivroVO)cbLivro.getSelectedItem();	
			}
			
			Integer capitulo = null;
			if(cbCapitulo != null) {
				capitulo = cbCapitulo.getSelectedIndex() + 1;
			}
			
			Integer versiculoDe = null;
			if(tfDe.getText() != null) {
				versiculoDe = Integer.parseInt(tfDe.getText());
			}
			
			Integer versiculoAte = null;
			if(tfAte.getText() != null) {
				versiculoAte = Integer.parseInt(tfAte.getText());
			}
			
			String texto = null;
			if(tfTexto != null) {
				texto = tfTexto.getText();
			}
			
			
			List<BibliaVO> bibliaVOs = servicoBeanLocal.listarVersiculo(livroSelecionado, capitulo, versiculoDe, versiculoAte, texto);
			
			
			if (bibliaVOs != null) {
				
				for (BibliaVO bibliaVO : bibliaVOs) {
					
					RowData rowData = new RowData();
					rowData.getValues().put(0, livroSelecionado);
					rowData.getValues().put(1, capitulo);
					rowData.getValues().put(2, bibliaVO.getVersiculo());
					rowData.getValues().put(3, bibliaVO.getTexto());
					
					rowData.setElement(bibliaVO);
					tableModel.addRow(rowData);
				}
			}
			
		} catch (BOException e) {
			JOptionPane.showMessageDialog(this, "Ocorreu um erro ao executar a operação.", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		

	}


	private void listarCapitulo() {
            LivroVO livroSelecionado = (LivroVO) cbLivro.getSelectedItem();
    		cbCapitulo.removeAllItems();

            for(Integer i = 1; i <= livroSelecionado.getQtd_capitulos(); i ++) {
                cbCapitulo.addItem(i);
            }
                
    }
	

	private void limpar() {
		this.tfTexto.setText(null);
		this.cbLivro.setSelectedIndex(0);
		this.cbCapitulo.setSelectedIndex(0);
		this.tfDe.setText(null);
		this.tfAte.setText(null);

	}
	
	private void fecharJanela() {
		this.setVisible(false);
		dispose();
	}
}
