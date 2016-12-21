class Price < ApplicationRecord
  belongs_to :products
  validates :amount, presence: true
end
