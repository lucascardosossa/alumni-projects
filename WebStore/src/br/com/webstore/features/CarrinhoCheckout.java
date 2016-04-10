//#if ${CarrinhoCheckout} == "T"
package br.com.webstore.features;

import java.awt.Color;
import java.awt.Desktop.Action;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.plaf.basic.CalendarRenderingHandler;

import br.com.webstore.dao.CarrinhoDao;
import br.com.webstore.dao.ProdutoDao;
import br.com.webstore.facade.GenericFacade;
import br.com.webstore.model.Carrinho;
import br.com.webstore.model.Produto;
import br.com.webstore.model.StatusVenda;
import br.com.webstore.model.Usuario;
import br.com.webstore.model.Venda;
import br.com.webstore.views.WebStoreEventMainScreenP;

import java.sql.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.List;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class CarrinhoCheckout extends JPanel {

	/**
	 * Serial version
	 */
	private static final long serialVersionUID = 8737213081913021625L;
	
	public static final String NAME = "Carrinho";

	/**
	 * Create the panel.
	 */
	Connection connection = null;
	private JTable table;
	
	private Usuario user;
	private GenericFacade facade;
	
	private static CarrinhoCheckout instance;
	
	public static CarrinhoCheckout getInstance(final GenericFacade gfacade, final Usuario usuario){
		if(instance == null)
			instance = new CarrinhoCheckout(gfacade, usuario);
		
		return instance;
	}
	
	
	public CarrinhoCheckout(final GenericFacade gfacade, final Usuario usuario) {
		this.user = usuario;
		this.facade = gfacade;
		criarLayout(null);
	}
	
	
	public void criarLayout(final Produto produto){
		this.removeAll();
		setLayout(null);
		//List<Venda> venda = gfacade.findVendaByUser(usuario,  gfacade.findStatusVendabyName("Carrinho"));
		final Carrinho carrinho = Carrinho.getInstance();
		boolean flag = false;
		if(produto != null){
			for (Map.Entry<Produto, Integer> hashProduto : carrinho.getMapCarrinho().entrySet()) {
				if(hashProduto.getKey().getId() == produto.getId()){
				    carrinho.getMapCarrinho().put(hashProduto.getKey(), hashProduto.getValue() + 1);
				    flag = true;
				}
				
			}
			
			if(!flag){
				carrinho.putMapCarrinho(produto, 1);
			}	
			
		}
		//Cabe�alho
		final Vector<String> headers = new Vector<String>(3);
		headers.addElement(new String("Codigo"));
		headers.addElement(new String("Descri��o"));
		headers.addElement(new String("Quantidade"));

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultTableModel modelCarrinho = new DefaultTableModel(headers, carrinho.getMapCarrinho().size());				
		CarrinhoCheckout.this.table.setModel(modelCarrinho);
		int row = 0;
		for (Map.Entry<Produto, Integer> hashProduto : carrinho.getMapCarrinho().entrySet()) {
			CarrinhoCheckout.this.table.getModel().setValueAt(hashProduto.getKey().getId().toString(), row, 0);
			CarrinhoCheckout.this.table.getModel().setValueAt(hashProduto.getKey().getDescricao(), row, 1);
			CarrinhoCheckout.this.table.getModel().setValueAt(hashProduto.getValue(), row, 2);
			row++;
		}		
		
		//table.setModel(new CarrinhoDao(null).getCarrinho(venda.get(0)));
		javax.swing.Action action = new AbstractAction()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        TableCellListener tcl = (TableCellListener)e.getSource();
		        int id = Integer.parseInt(tcl.getTable().getValueAt(tcl.getRow(), 0).toString());
		        
		       for (Map.Entry<Produto, Integer> hashProduto : carrinho.getMapCarrinho().entrySet()) {
					if(hashProduto.getKey().getId() == id){
					    carrinho.getMapCarrinho().put(hashProduto.getKey(), Integer.parseInt(tcl.getNewValue().toString()));
					}
					
				}
       
/*		        System.out.println("Row   : " + tcl.getRow());
		        System.out.println("Column: " + tcl.getColumn());
		        System.out.println("Old   : " + tcl.getOldValue());
		        System.out.println("New   : " + tcl.getNewValue());*/
		    }
		};

		TableCellListener tcl = new TableCellListener(table, action);
		
		
		table.setBounds(22, 24, 361, 76);	
		table.setModel(modelCarrinho);
		
		add(table);
		
		/*JButton btnDetalhes = new JButton("Excluir");
		btnDetalhes .addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ListSelectionModel lsm = CarrinhoCheckout.this.table.getSelectionModel();
				int index = lsm.getLeadSelectionIndex();
				
				if (index == -1) {
					JOptionPane.showMessageDialog(null, "� necess�rio selecionar um item.");
				} else {
					Integer id = (Integer) CarrinhoCheckout.this.table.getValueAt(index, 0);
					
					//ProdutoDetalhes pd = new ProdutoDetalhes(id, usuarioLogado, gfacade);
					
					final JDialog frame = new JDialog();
					
					frame.setModal(true);
					frame.setResizable(false);
					frame.setBounds(500, 300, 460, 200);
				//	frame.getContentPane().add(pd);
					frame.setVisible(true);
				}
				
			}
		});
		btnDetalhes .setBounds(440, 273, 90, 23);
		this.add(btnDetalhes );*/
	}
}
//#endif
