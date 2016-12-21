class CreatePrices < ActiveRecord::Migration[5.0]
  def change
    create_table :prices do |t|
      t.references :product, foreign_key: true
      t.decimal :amount

      t.timestamps
    end
  end
end
