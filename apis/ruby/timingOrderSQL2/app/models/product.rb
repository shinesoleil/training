class Product < ApplicationRecord
  has_many :prices
  validate :name, presence: true
end
