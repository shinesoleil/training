class CreatePrices < ActiveRecord::Migration[5.0]
  def change
    create_table :prices do |t|
      t.decimal :amount
      t.references :product, foreign_key: true

      t.timestamps
    end
  end
end
