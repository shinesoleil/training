class CreateOrderItems < ActiveRecord::Migration[5.0]
  def change
    create_table :order_items do |t|
      t.references :order, foreign_key: true
      t.references :products, foreign_key: true
      t.integer :quantity
      t.decimal :price
      t.decimal :subtotal

      t.timestamps
    end
  end
end
