class Order < ApplicationRecord
  belongs_to :customer
  has_many :order_items
  has_one :payment

  validates :total_price, presence: true
end
