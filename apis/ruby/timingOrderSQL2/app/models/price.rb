class Price < ApplicationRecord
  belongs_to :product
  validate :amount, presence: true
end
