class OrderItem < ApplicationRecord
  belongs_to :order
  belongs_to :products

  validates :quantity, :price, :subtotal, presence: true
end
