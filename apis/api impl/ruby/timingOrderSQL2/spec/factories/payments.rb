FactoryGirl.define do
  factory :payment do
    order
    amount 9.99

    trait :without_amount do
      amount nil
    end
  end
end
