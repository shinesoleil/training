class OrderItem < ApplicationRecord
  belongs_to :order
  belongs_to :product

  validate :quantity, :price, :subtotal, presence: true
end
