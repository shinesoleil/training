FactoryGirl.define do
  factory :order_item do
    order
    product
    quantity 1
    sub_total "9.99"
  end
end
