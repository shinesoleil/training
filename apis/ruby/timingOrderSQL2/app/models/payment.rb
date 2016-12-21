class Payment < ApplicationRecord
  belongs_to :order

  validate :amount, presence: true
end
