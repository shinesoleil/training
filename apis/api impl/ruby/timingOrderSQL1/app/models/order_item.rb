class OrderItem < ApplicationRecord
  belongs_to :product
  belongs_to :order

  validates :price, :quantity, :subtotal, presence: true
end
