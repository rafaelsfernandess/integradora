package br.com.empresa.view;

import java.awt.BorderLayout;
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
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumnModel;

import br.com.empresa.exception.BOException;
import br.com.empresa.service.IServicoBeanLocal;
import br.com.empresa.service.ServicoBeanLocal;
import br.com.empresa.view.util.RowData;
import br.com.empresa.view.util.TableModel;
import br.com.empresa.vo.BibliaVO;
import br.com.empresa.vo.LivroVO;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class ConsultaBibliaView extends JDialog {

	private final JPanel Panel = new JPanel();
	private JTextField tfTexto;
	private JTable table;
	private TableModel tableModel;
	private JComboBox cbLivro;
	private JComboBox cbCapitulo;
	private JComboBox cbVersiculo;

	/**
	 * Create the dialog.
	 */
	
	private IServicoBeanLocal servicoBeanLocal;

	public ConsultaBibliaView() {
		
		servicoBeanLocal = new ServicoBeanLocal();

		
		setTitle("Consulta Biblia");
		setBounds(100, 100, 714, 457);
		getContentPane().setLayout(null);
		Panel.setBounds(10, 11, 332, 362);
		Panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		getContentPane().add(Panel);
		Panel.setLayout(null);

		JLabel lblLivro = new JLabel("Livro");
		lblLivro.setBounds(10, 11, 34, 14);
		Panel.add(lblLivro);

		JLabel lblCapitulo = new JLabel("Capitulo");
		lblCapitulo.setBounds(203, 11, 60, 14);
		Panel.add(lblCapitulo);

		JLabel lblVersiculo = new JLabel("Versiculo");
		lblVersiculo.setBounds(10, 66, 60, 14);
		Panel.add(lblVersiculo);

		JLabel lblTexto = new JLabel("Texto");
		lblTexto.setBounds(10, 117, 46, 14);
		Panel.add(lblTexto);

		tfTexto = new JTextField();
		tfTexto.setBounds(10, 133, 312, 23);
		Panel.add(tfTexto);
		tfTexto.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(354, 11, 330, 362);
		getContentPane().add(scrollPane);
			
		tableModel = new TableModel();
		tableModel.addColumn("Livro");
		tableModel.addColumn("Capitulo");
		tableModel.addColumn("Versiculo");

		table = new JTable(tableModel);
		table.setAutoscrolls(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		TableColumnModel tableColumnModel = table.getColumnModel();
		tableColumnModel.getColumn(0).setPreferredWidth(100);
		tableColumnModel.getColumn(1).setPreferredWidth(100);
		tableColumnModel.getColumn(2).setPreferredWidth(100);


		pesquisar();

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 315, 312, 2);
		Panel.add(separator);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBackground(UIManager.getColor("Button.light"));
		btnPesquisar.setForeground(Color.BLACK);
		btnPesquisar.setBounds(109, 328, 100, 23);
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisar();
			}

		});
		Panel.add(btnPesquisar);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(10, 328, 89, 23);
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		Panel.add(btnLimpar);

		cbLivro = new JComboBox();
		cbLivro.setBounds(10, 29, 119, 22);
		cbLivro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listarLivros();
			}
		});
		DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel<>();
		cbLivro.setModel(defaultComboBoxModel);
		defaultComboBoxModel.insertElementAt(null, 0);
		Panel.add(cbLivro);

		cbCapitulo = new JComboBox();
		cbCapitulo.setBounds(203, 33, 119, 22);
		Panel.add(cbCapitulo);

		cbVersiculo = new JComboBox();
		cbVersiculo.setBounds(10, 84, 119, 22);
		Panel.add(cbVersiculo);
				
		JButton btnFechar = new JButton("Fechar");
		btnFechar.setBounds(595, 384, 89, 23);
		getContentPane().add(btnFechar);

		inicializarComponentes();

	}

	private void inicializarComponentes() {
		servicoBeanLocal = new ServicoBeanLocal();
		try {
			List<LivroVO>livros = servicoBeanLocal.listarLivros();
			System.out.println(livros);
			for(LivroVO livroVO : livros) {
				cbLivro.addItem(livroVO);
			}
		}catch(BOException e) {
			JOptionPane.showMessageDialog(this, "Ocorreu um erro");
			e.printStackTrace();
		}
	}

	private void pesquisar() {

		

	}

	private void limpar() {
		this.tfTexto.setText(null);
		this.cbLivro.setSelectedIndex(0);
		this.cbCapitulo.setSelectedIndex(0);
		this.cbVersiculo.setSelectedIndex(0);
		//pesquisar();

	}

	private void listarLivros() {
        if(cbLivro.getSelectedItem() != null) {
            LivroVO livroSelecionado = (LivroVO) cbLivro.getSelectedItem();
            
            for(Integer i = 1; i <= livroSelecionado.getQtd_capitulos(); i ++) {
                cbCapitulo.addItem(i);
                
            }
            
            cbCapitulo.setEditable(true);
            cbVersiculo.setEditable(true);
        }
        
        else {
            cbCapitulo.removeAllItems();
            cbVersiculo.removeAllItems();
        }         
    }
}
