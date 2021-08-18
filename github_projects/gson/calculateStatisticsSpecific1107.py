import csv, operator, statistics, re

li = []
li2 = []
top_fitness_list = []
top_memory_usage_list = []


def fitness_improvement(sorted_fitness_list):
    for item in sorted_fitness_list:
        li.append(item[6])
    li.remove('FitnessImprovement')

    samples = []
    for item in li:
        samples.append(float(item))

    samples = sorted(samples, reverse=True)
    top_fitness_list.append(samples[0])


def memory_usage(sorted_fitness_list):
    top_value = 0
    for item in sorted_fitness_list:
        top_value += 1

        if top_value == 2:
            li2.append(item[7])
            break

    samples = []
    for item in li2:
        samples.append(float(item))

    samples = sorted(samples)
    top_memory_usage_list.append(samples[0])


# Targetting specific patch now

target_patch_is_highest_list = []

def memory_usage_specific(sorted_fitness_list):
    num = 0
    for item in sorted_fitness_list:
        num += 1
        if num == 10:
            break

        if item[2] and item[3] == "true":
            if "| gin.edit.statement.DeleteStatement \"./gson/src/main/java/com/google/gson/GsonBuilder.java\":1107 |" == item[1]:
                    print(item[2], item[3])
                    print(item[1])
                    target_patch_is_highest_list.append(item)


        # if "DeleteStatement" and "\":1107 |" in item[1]:
        #     if "CopyStatement" or "SwapStatement" not in item[1]:
        #         target_patch_is_highest_list.append(item)

def get_memory_usage_specific():
    memory_usage_list = []
    for target_patch in target_patch_is_highest_list:
        memory_usage_list.append(float(target_patch[7]))
    print(memory_usage_list)
    return memory_usage_list

original_memory_usage_list = []
def original_memory_usage(sorted_fitness_list):
    list = []
    for item in sorted_fitness_list:
        if item[1] == "|":
            print(item)
            list.append(float(item[7]))

    average = sum(list) / len(list)
    original_memory_usage_list.append(average)

original_execution_time_list = []
def original_execution_time(sorted_fitness_list):
    list = []
    for item in sorted_fitness_list:
        if item[1] == "|":
            print(item)
            list.append(float(item[4]))

    average = sum(list) / len(list)
    original_execution_time_list.append(average)


execution_time_list = []
def execution_time(sorted_fitness_list):
    num = 0
    for item in sorted_fitness_list:
        num += 1
        if num == 10:
            break

        if item[2] and item[3] == "true":
            if "| gin.edit.statement.DeleteStatement \"./gson/src/main/java/com/google/gson/GsonBuilder.java\":1107 |" == item[1]:
                execution_time_list.append(float(item[4]))





for test_number in range(10):
    import csv

    with open('runs_with_jj/sampler_results{}.csv'.format(test_number), 'r') as file:
        reader = csv.reader(file)

        # Sort by FitnessImprovement
        sorted_fitness_list = sorted(reader, key=operator.itemgetter(6), reverse=True)
        fitness_improvement(sorted_fitness_list)
        memory_usage(sorted_fitness_list)

        # Testing
        memory_usage_specific(sorted_fitness_list)
        original_memory_usage(sorted_fitness_list)
        execution_time(sorted_fitness_list)
        original_execution_time(sorted_fitness_list)


print("")
print("FitnessImprovement Variance {:.3f}".format((statistics.variance(top_fitness_list))))
print("MemoryUsed (MB) Variance {:.3f} MB".format((statistics.variance(top_memory_usage_list))))
print("Average Lowest Memory {:.3f} MB".format(sum(top_memory_usage_list) / len(top_memory_usage_list)))

print("")
print("Original MemoryUsed (MB) Variance {:.3f} MB".format((statistics.variance(original_memory_usage_list))))
print("Original Execution Time Average {:.3f}".format(sum(original_execution_time_list) / len(original_execution_time_list)))

print("")
print("MemoryUsed (MB) Variance (DeleteStatement 1077) {:.3f} MB".format((statistics.variance(get_memory_usage_specific()))))
print("1107 Execution Time Average {:.3f}".format(sum(execution_time_list) / len(execution_time_list)))




