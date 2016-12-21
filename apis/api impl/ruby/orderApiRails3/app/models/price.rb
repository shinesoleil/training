class Price < ApplicationRecord
  belongs_to :product

  validates :amount, :product_id, presence: true
end
